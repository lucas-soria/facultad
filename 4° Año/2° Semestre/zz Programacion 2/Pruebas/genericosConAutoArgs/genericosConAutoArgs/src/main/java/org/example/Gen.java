package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gen<T> {
    private T object;

    public void add(T object) {
        this.object = object;
    }
}
