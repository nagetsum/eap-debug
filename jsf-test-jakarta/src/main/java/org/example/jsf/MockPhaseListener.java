package org.example.jsf;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class MockPhaseListener implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("### MockPhaseListener.afterPhase phase= " + event.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("### MockPhaseListener.beforePhase phase= " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
