package hu.webuni.hr.szabi.dto.mytype;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

public class CompanyMap extends HashMap {

    @Override
    public Object get(Object key) {
        Object o = super.get(key);
        if (o == null)  throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return o;
    }

}
