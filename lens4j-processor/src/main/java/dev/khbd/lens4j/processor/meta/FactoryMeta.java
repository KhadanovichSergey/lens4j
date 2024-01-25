package dev.khbd.lens4j.processor.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Factory metadata.
 *
 * @author Alexey_Bodyak
 */
@Getter
@RequiredArgsConstructor
public class FactoryMeta {

    private final String packageName;
    private final String factoryName;
    private final Set<Modifier> modifiers;
    private final List<LensMeta> lenses = new ArrayList<>();

    /**
     * Add lens meta to factory.
     *
     * @param lensMeta lens meta
     */
    public void addLens(LensMeta lensMeta) {
        this.lenses.add(lensMeta);
    }
}
