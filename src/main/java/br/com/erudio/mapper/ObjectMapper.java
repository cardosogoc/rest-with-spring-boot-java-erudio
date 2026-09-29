package br.com.erudio.mapper;

import br.com.erudio.data.dto.BooksDTO;
import br.com.erudio.model.Books;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();
        for (Object o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

    public static BooksDTO parseObject(Books origin) {
        return mapper.map(origin, BooksDTO.class);
    }

    public static List<BooksDTO> parseListObjects(List<Books> origin) {
        List<BooksDTO> destinationObjects = new ArrayList<BooksDTO>();

        for (Books o : origin) {
            destinationObjects.add(mapper.map(o, BooksDTO.class));
        }

        return destinationObjects;
    }
}
