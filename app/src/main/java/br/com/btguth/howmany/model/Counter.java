package br.com.btguth.howmany.model;

public class Counter {

    Integer idCounter;
    String counterName;
    String measureUnityName;
    String measureUnityAlias;
    Integer multiplier;
    Integer counterColor;
    String clickAction;
    Integer counterValue;

    public Counter(){

    }

    public Integer getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(Integer idCounter) {
        this.idCounter = idCounter;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getMeasureUnityName() {
        return measureUnityName;
    }

    public void setMeasureUnityName(String measureUnityName) {
        this.measureUnityName = measureUnityName;
    }

    public String getMeasureUnityAlias() {
        return measureUnityAlias;
    }

    public void setMeasureUnityAlias(String measureUnityAlias) {
        this.measureUnityAlias = measureUnityAlias;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public Integer getCounterColor() {
        return counterColor;
    }

    public void setCounterColor(Integer counterColor) {
        this.counterColor = counterColor;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }

    public Integer getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(Integer counterValue) {
        this.counterValue = counterValue;
    }
}
