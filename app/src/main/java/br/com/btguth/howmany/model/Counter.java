package br.com.btguth.howmany.model;

public class Counter {

    Integer idCounter;
    String counterName;
    String measureUnityName;
    String measureUnityAlias;
    String multiplier;
    Integer counterColor;
    String clickAction;
    String counterValue;

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
        counterName = counterName;
    }

    public String getMeasureUnityName() {
        return measureUnityName;
    }

    public void setMeasureUnityName(String measureUnityName) {
        measureUnityName = measureUnityName;
    }

    public String getMeasureUnityAlias() {
        return measureUnityAlias;
    }

    public void setMeasureUnityAlias(String measureUnityAlias) {
        measureUnityAlias = measureUnityAlias;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
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

    public String getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(String counterValue) {
        this.counterValue = counterValue;
    }
}
