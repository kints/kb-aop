package mx.com.citelis.kb;

import org.aspectj.util.LangUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryKBRepository implements Repository<KBModel>, Serializable {

    private List<KBModel> kbs = new ArrayList()
        {{
            add(new KBModel("HW with Spring Boot", "", ""));
            add(new KBModel("Security with Spring Boot", "", ""));
            add(new KBModel("Actuator with Spring Boot", "", ""));
        }};

    @Override
    public Iterable<KBModel> findAll() {
        return this.kbs;
    }

    @Override
    public KBModel save(KBModel kbmodel) {
        this.kbs.add(kbmodel);
        return kbmodel;
    }

    @Override
    @RavisaAudit
    public Iterable<KBModel> findBy(Predicate<KBModel> predicate) throws Exception {
        //int alv = 5/0;
          if (true)
            throw new Exception("Algun error BB");
        return this.kbs.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
