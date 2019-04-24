package mx.com.citelis.kb;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class KbAopApplication {
	@RavisaAudit

	public static void main(String[] args) throws Exception {
		ApplicationContext context =
				SpringApplication.run(KBConfiguration.class);

		KBService service = context.getBean(KBService.class);
		KBModel modelset = new KBModel();
		modelset.setCode("Code");
		modelset.setTitle("Title");
		modelset.setDescription("Description");
		service.createKB(modelset);
		KBModel model = service.createKB(new KBModel("Que esta pasando!!!", "", "") );
		service.getAllKB().forEach(System.out::println);
		service.findByTitleContain("Boot");
		//System.out.println("Antes de traer 3 veces");
		/*service.getAllKB().forEach(System.out::println);
		service.getAllKB().forEach(System.out::println);
		service.getAllKB().forEach(System.out::println);

		System.out.println("==================================");
		service.createKB(new KBModel("XXXXX", "", ""));
		service.getAllKB().forEach(System.out::println);
	/*	service.getAllKB().forEach(System.out::println);
		service.getAllKB().forEach(System.out::println);*/
	}

}
