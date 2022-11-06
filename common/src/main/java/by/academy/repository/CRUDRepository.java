package by.academy.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository <K,T>{
    T findById(K id);

}
