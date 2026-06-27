package xbuild.Events;

public class LoadingDoneEvent extends XBaseEvent {

    public enum Phase {
        ESSENTIAL,
        COMPLETE
    }

    private final boolean valid;
    private final Phase phase;

    public LoadingDoneEvent(Object source, boolean valid) {
        this(source, valid, Phase.COMPLETE);
    }

    public LoadingDoneEvent(Object source, boolean valid, Phase phase) {
        super(source);
        this.valid = valid;
        this.phase = phase;
    }

    public boolean isValid() {
        return valid;
    }

    public Phase getPhase() {
        return phase;
    }
}
