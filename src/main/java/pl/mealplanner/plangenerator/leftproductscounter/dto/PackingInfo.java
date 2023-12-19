package pl.mealplanner.plangenerator.leftproductscounter.dto;

public record PackingInfo(
        String productName,
        int nrOfPackets,
        float quantityInPacket
) {
}
