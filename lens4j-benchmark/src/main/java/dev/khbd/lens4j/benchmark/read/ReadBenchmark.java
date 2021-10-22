package dev.khbd.lens4j.benchmark.read;

import dev.khbd.lens4j.benchmark.domain.Account;
import dev.khbd.lens4j.benchmark.domain.Currency;
import dev.khbd.lens4j.benchmark.domain.Payment;
import dev.khbd.lens4j.benchmark.util.PaymentUtil;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * @author Sergei_Khadanovich
 */
@Fork(value = 1, warmups = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@OperationsPerInvocation(ReadBenchmark.OPS)
public class ReadBenchmark {

    public static final int OPS = 100_000;

    @Benchmark
    public void argumentIsNull_manual(Blackhole hole) {
        for (int i = 0; i < OPS; i++) {
            String code = PaymentUtil.manualGet(null);
            hole.consume(code);
        }
    }

    @Benchmark
    public void argumentIsNull_optional(Blackhole hole) {
        for (int i = 0; i < OPS; i++) {
            String code = PaymentUtil.optionalGet(null);
            hole.consume(code);
        }
    }

    @Benchmark
    public void argumentIsNull_lens(Blackhole hole) {
        for (int i = 0; i < OPS; i++) {
            String code = PaymentUtil.lensGet(null);
            hole.consume(code);
        }
    }

    @Benchmark
    public void allNotNull_manual(Blackhole hole, AllNotNullState state) {
        for (int i = 0; i < OPS; i++) {
            String code = PaymentUtil.manualGet(state.payment);
            hole.consume(code);
        }
    }

    @Benchmark
    public void allNotNull_optional(Blackhole hole, AllNotNullState state) {
        for (int i = 0; i < OPS; i++) {
            String code = PaymentUtil.optionalGet(state.payment);
            hole.consume(code);
        }
    }

    @Benchmark
    public void allNotNull_lens(Blackhole hole, AllNotNullState state) {
        for (int i = 0; i < OPS; i++) {
            String code = PaymentUtil.lensGet(state.payment);
            hole.consume(code);
        }
    }

    @State(Scope.Benchmark)
    public static class AllNotNullState {

        public Payment payment;

        @Setup(Level.Trial)
        public void setUp() {
            Currency currency = new Currency();
            currency.setCode("810");

            Account account = new Account();
            account.setCurrency(currency);

            payment = new Payment();
            payment.setPayerAccount(account);
        }
    }
}
