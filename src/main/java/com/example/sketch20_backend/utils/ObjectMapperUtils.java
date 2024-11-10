package com.example.sketch20_backend.utils;

import com.example.sketch20_backend.dto.CanapeDTO;
import com.example.sketch20_backend.dto.ChambreDTO;
import com.example.sketch20_backend.dto.ProductDTO;
import com.example.sketch20_backend.dto.TableDTO;
import com.example.sketch20_backend.model.Canape;
import com.example.sketch20_backend.model.Chambre;
import com.example.sketch20_backend.model.Product;
import com.example.sketch20_backend.model.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapperUtils {

    private static final ModelMapper modelMapper = new ModelMapper();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperUtils() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass) {
        return entityList.stream()
                .map(entity -> map(entity, outClass))
                .collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.treeToValue(n, valueType);
    }

    public static <T> T convertValue(Object fromValue, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.convertValue(fromValue, valueType);
    }

    public static ProductDTO convertToDto(Product product) {
        return map(product, ProductDTO.class);
    }

    public static Product convertToEntity(ProductDTO productDTO) {
        return map(productDTO, Product.class);
    }

    public static CanapeDTO convertToDto(Canape canape) {
        return map(canape, CanapeDTO.class);
    }

    public static Canape convertToEntity(CanapeDTO canapeDTO) {
        return map(canapeDTO, Canape.class);
    }

    public static ChambreDTO convertToDto(Chambre chambre) {
        return map(chambre, ChambreDTO.class);
    }

    public static Chambre convertToEntity(ChambreDTO chambreDTO) {
        return map(chambreDTO, Chambre.class);
    }

    public static TableDTO convertToDto(Table table) {
        return map(table, TableDTO.class);
    }

    public static Table convertToEntity(TableDTO tableDTO) {
        return map(tableDTO, Table.class);
    }
}
