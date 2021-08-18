package C482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.stream.Collectors;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) throws Exception {
        for (Part part : allParts) {
            if (part.getId() == partId) return part;
        }
        throw new Exception("Part Not Found!");
    }

    public static Product lookupProduct(int productId) throws Exception {
        for (Product product : allProducts) {
            if (product.getId() == productId) return product;
        }
        throw new Exception("Product Not Found!");
    }

    public static ObservableList<Part> lookupPart(String partName) {
        return allParts.stream().filter(part -> part.getName().contains(partName)).collect(
                Collectors.toCollection(FXCollections::observableArrayList));
        /*above return statement found at
            https://stackoverflow.com/questions/53075175/observablelist-returns-sublist-that-matches*/
        //TODO: might need to connect this up with the controller
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        return allProducts.stream().filter(product -> product.getName().contains(productName)).collect(
                Collectors.toCollection(FXCollections::observableArrayList));
        /*above return statement found at
            https://stackoverflow.com/questions/53075175/observablelist-returns-sublist-that-matches*/
    }

    public static void updatePart(int index, Part selectedPart) {
        /*
        TODO: figure out what to do with this. (I think I may just need to call the controller to bring up the modify part scene
         */
    }

    public static void updateProduct(int index, Product selectedPart) {
        /*
        TODO: I think I just need to call the controller to bring up the modify product scene?
         */
    }
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) throws Exception {
        if (allProducts.contains(selectedProduct)) {
            if (selectedProduct.getAllAssociatedParts().size() == 0) {
                allProducts.remove(selectedProduct);
                return true;
            }
            throw new Exception("Product Cannot Be Deleted! (Associated Parts Must Be Removed First)");
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    @FXML
    public void initialize() {
        
    }

}
