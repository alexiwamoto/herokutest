package org.edmbrasil.www;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.edmbrasil.www.domain.Worker;
import org.edmbrasil.www.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private WorkerService workerService;

    @Autowired
    public GreetingController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name+" Alterado"));
    }

    @RequestMapping(value = "/greetings",  method = RequestMethod.GET)
    public List<Greeting> greeting2() {
        List<Greeting> greetingList = new ArrayList<>();
        String stringToConcat = "";
        Greeting greeting ;
        for(int i = 0; i<10; i++){
            stringToConcat = stringToConcat + counter.incrementAndGet();
            greeting = new Greeting(counter.incrementAndGet(), stringToConcat);
            greetingList.add(greeting);
        }
        return greetingList;
    }

    @RequestMapping(value = "/worker", method = RequestMethod.GET)
    public Worker saveWorker(@RequestParam(value = "name")String name){
        Worker worker = new Worker();
        worker.setName(name);
        worker.setLastname("Iwamoto");
        worker.setType("Empregado");
        workerService.save(worker);
        return worker;
    }

}
