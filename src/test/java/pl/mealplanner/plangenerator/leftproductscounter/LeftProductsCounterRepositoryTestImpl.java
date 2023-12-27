package pl.mealplanner.plangenerator.leftproductscounter;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.mealplanner.plangenerator.leftproductscounter.entity.PackingMeasures;
import pl.mealplanner.plangenerator.leftproductscounter.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class LeftProductsCounterRepositoryTestImpl implements LeftProductsCounterRepository{

    private final Map<String, Product> products = new HashMap<>();

    LeftProductsCounterRepositoryTestImpl(){
        products.put("mleko", new Product("mleko", List.of(
                new PackingMeasures(500f, "ml")
        )));
        products.put("kasza", new Product("kasza", List.of(
                new PackingMeasures(180f, "g"),
                new PackingMeasures(450f, "g"),
                new PackingMeasures(600f, "g")
        )));
        products.put("jogurt", new Product("jogurt", List.of(
                new PackingMeasures(150f, "g"),
                new PackingMeasures(250f, "g")
//                new PackingMeasures(500f, "kg")
        )));
        products.put("marchew", new Product("marchew", List.of(
                new PackingMeasures(0f, "g"),
                new PackingMeasures(0f, "szt")
        )));
    }

    @Override
    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(products.get(name));
    }

    @Override
    public <S extends Product> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Product> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Product> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Product> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Product> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }
}
