package flavia.dev.delivery_restaurantes.domain;

public enum PedidoStatus {
    RECEIVED,
    PENDING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
