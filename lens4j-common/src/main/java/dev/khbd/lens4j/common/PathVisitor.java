package dev.khbd.lens4j.common;

/**
 * Path visitor.
 *
 * @author Sergei_Khadanovich
 */
@Deprecated(since = "0.2.1", forRemoval = true)
public interface PathVisitor {

    default void start() {
    }

    default void visitPoint(Point point) {
    }

    default void visitProperty(Property property) {
    }

    default void visitMethod(Method method) {
    }

    default void finish() {
    }
}
