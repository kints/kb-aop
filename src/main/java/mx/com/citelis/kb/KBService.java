package mx.com.citelis.kb;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class KBService {

    private Repository<KBModel> repository;

    public KBService(Repository kbRepository){
        this.repository = kbRepository;
    }

    @RavisaAudit
    @Cacheable("xxxx")
    @CitelisLog(prefix = "Method called: ")
    public Iterable<KBModel> getAllKB() throws InterruptedException {
        Thread.sleep(6000);
        return this.repository.findAll();
    }

    @RavisaAudit
    @CacheEvict("xxxx")
    public KBModel createKB(KBModel model){
        KBModel result = this.repository.save(model);
        return result;
    }

    public Iterable<KBModel> findByTitleContain(String text) throws Exception{
        return this.repository
                .findBy(kbModel -> kbModel.getTitle().contains(text));
    }

    public Iterable<KBModel> findByDescriptionContains(String text) throws Exception{
        return this.repository
                .findBy(kbModel -> kbModel.getDescription().contains(text));
    }

    public Iterable<KBModel> findByCodeContains(String text)throws Exception{
        return this.repository
                .findBy(kbModel -> kbModel.getCode().contains(text));
    }

}
