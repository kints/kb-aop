package mx.com.citelis.kb;

import java.util.function.Predicate;

//TODO: Read more about Java Generics
public interface Repository<T> {

    Iterable<T> findAll();
    T save(T kbmodel);
    Iterable<T> findBy(Predicate<T> predicate) throws Exception;

}
