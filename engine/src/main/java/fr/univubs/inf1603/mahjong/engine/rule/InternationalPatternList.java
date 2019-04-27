package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * Implementation of the {@link AbstractPatternList} for the international ruleset.
 * This class behaves like a singleton
 */
public class InternationalPatternList implements AbstractPatternList {

    public final static InternationalPatternList DEFAULT = new InternationalPatternList(InternationalPatterns.values());

    private IdentifiablePattern[] patterns;

    public InternationalPatternList(IdentifiablePattern ... patterns) {
        this.patterns = patterns;
    }

    @Override
    public IdentifiablePattern[] getPatterns() {
        return this.patterns;
    }

   }
