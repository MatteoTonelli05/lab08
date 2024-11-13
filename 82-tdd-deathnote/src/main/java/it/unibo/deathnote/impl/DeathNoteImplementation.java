package it.unibo.deathnote.impl;

import java.sql.Time;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {
    public class DeathData {
        private String name;
        private String cause;
        private String details;

        public DeathData(String name, String cause, String details) {
            this.setName(name);
            this.setCause(cause);
            this.setDetails(details);
        }

        public DeathData(String name) {
            this(name, null, null);
        }

        public String getName() {
            return name;
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }

    private List<DeathData> deaths;
    long timeLastWrite;

    public DeathNoteImplementation() {
        deaths = new LinkedList<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber <= 0) {
            throw new IllegalArgumentException("error: invalid argument");
        } else {
            return DeathNote.RULES.get(ruleNumber);
        }
    }

    @Override
    public void writeName(String name) {
        if (!this.isNameWritten(name)) {
            deaths.add(new DeathData(name));
            timeLastWrite = System.currentTimeMillis();
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        long tmp = System.currentTimeMillis();
        if (timeLastWrite - tmp < 40) {
            deaths.getLast().setCause(cause);
            return true;
        }
        deaths.remove(deaths.getLast());
        return false;
    }

    @Override
    public boolean writeDetails(String details) {
    }

    @Override
    public String getDeathCause(String name) {
        Iterator<DeathData> iter = deaths.iterator();
        while (iter.hasNext()) {
            DeathData deathData = iter.next();
            if (deathData.name.equals(name)) {
                return deathData.cause;
            }
        }
        return null;
    }

    @Override
    public String getDeathDetails(String name) {
        Iterator<DeathData> iter = deaths.iterator();
        while (iter.hasNext()) {
            DeathData deathData = iter.next();
            if (deathData.name.equals(name)) {
                return deathData.details;
            }
        }
        return null;
    }

    @Override
    public boolean isNameWritten(String name) {
        Iterator<DeathData> iter = deaths.iterator();
        while (iter.hasNext()) {
            DeathData deathData = iter.next();
            if (deathData.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

}