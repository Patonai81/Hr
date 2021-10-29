package hu.webuni.hr.szabi.dto.mytype;

import hu.webuni.hr.szabi.exception.CompanyCouldNotBeManipulatedException;
import hu.webuni.hr.szabi.exception.CompanyNotFoundException;

import java.util.HashMap;

public class CompanyMap<K,V> extends HashMap<K,V> {

    @Override
    public V get(Object key) {
        V o = super.get(key);
        if (o == null)  throw new CompanyNotFoundException("Nincs ilyen cég id"+key);
        return o;
    }

    public V put(K key, V value, boolean replace) {
        if ( key == null){
            throw new CompanyCouldNotBeManipulatedException("A megadott id nem lehet null");
        }
        if (replace && !super.containsKey(key)){
           throw  new CompanyCouldNotBeManipulatedException("A megadott id nem létezik, így lecserélni sem lehet");
        }
        return super.put(key, value);
    }

    @Override
    public V remove(Object key) {
        if (!super.containsKey(key)){
            throw  new CompanyCouldNotBeManipulatedException("A megadott id nem létezik, így törölni sem lehet");
        }
        return super.remove(key);
    }
}
