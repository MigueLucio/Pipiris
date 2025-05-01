package com.pipiris.tienda.dto.inventario;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ReabastecerProductoDTO {
	
    @NotNull
    private Long productoId;

    @NotEmpty
    private List<InventarioReabastecerDTO> items;

}
