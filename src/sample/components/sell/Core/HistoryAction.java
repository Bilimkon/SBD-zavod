package sample.components.sell.Core;

public class HistoryAction {
    private float creditCost;
    private float cardCost;
    private String creditDescription;
    private String actionTime;

    public HistoryAction(float creditCost, float cardCost, String creditDescription, String actionTime) {
        this.creditCost = creditCost;
        this.cardCost = cardCost;
        this.creditDescription = creditDescription;
        this.actionTime = actionTime;
    }

    public String getCreditDescription() {
        return creditDescription;
    }

    public void setCreditDescription(String creditDescription) {
        this.creditDescription = creditDescription;
    }

    public float getCardCost() {
        return cardCost;
    }

    public void setCardCost(float cardCost) {
        this.cardCost = cardCost;
    }

    public float getCreditCost() {
        return creditCost;
    }

    public void setCreditCost(float creditCost) {
        this.creditCost = creditCost;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }
}
