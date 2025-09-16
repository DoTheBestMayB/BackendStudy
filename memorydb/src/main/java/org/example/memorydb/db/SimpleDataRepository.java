package org.example.memorydb.db;

import org.example.memorydb.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

// 와일드카드를 이용해 제네릭 타입에 제한을 둬 필요한 함수 호출할 수 있도록 설정 ex) setId
abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {

    private List<T> dataList = new ArrayList<T>();

    private static long index = 1;

    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    // create, update
    @Override
    public T save(T data) {
        if (Objects.isNull(data)) {
            throw new RuntimeException("Data is null");
        }

        // db에 데이터가 이미 있는지 확인
        var prevData = dataList.stream()
                .filter(it -> it.getId().equals(data.getId()))
                .findFirst();

        if (prevData.isPresent()) { // create
            dataList.remove(prevData.get());
            dataList.add(data);
        } else { // update
            data.setId(index++);
            dataList.add(data);
        }

        return data;
    }

    // read
    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream()
                .filter(it -> {
                    return it.getId().equals(id);
                })
                .findFirst();
    }

    @Override
    public List<T> findAll() {
        return dataList.stream().sorted(sort).collect(Collectors.toList());
    }

    // delete

    @Override
    public void delete(ID id) {
        var deleteEntity = dataList.stream()
                .filter(it -> {
                    return it.getId().equals(id);
                })
                .findFirst();
        if (deleteEntity.isPresent()) {
            dataList.remove(deleteEntity.get());
        }
    }
}
