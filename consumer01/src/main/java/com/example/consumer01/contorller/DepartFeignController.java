package com.example.consumer01.contorller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.consumer01.bean.Depart;
import com.example.consumer01.interfaces.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/depart")
public class DepartFeignController {

    private final DepartService service;

    @Value("${server.port}")
    private String port;
    @Autowired
    public DepartFeignController(@Qualifier("com.example.consumer01.interfaces.DepartService") DepartService service) {
        this.service = service;
    }

    @GetMapping("/port")
    public String getPort(){
        return port;
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart){
        return service.save(depart);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return service.removeById(id);
    }

    @PutMapping("/")
    public boolean update(@RequestBody Depart depart){
        return service.update(depart);
    }

//    @GetMapping("/{id}")
//    @SentinelResource(value = "getHandle",fallback = "handleGet",blockHandler = "handleGetBlock")
//    public Depart getById(@PathVariable Integer id){
//        try (Entry entry = SphU.entry("listHandle")) {
//            return service.getById(id);
//        } catch (BlockException e) {
//            Depart depart = new Depart();
//            depart.setId(2).setName("entry get sentinel flow fallback !!");
//            return depart;
//        }
//    }
    @GetMapping("/{id}")
    @SentinelResource(value = "getHandle",blockHandler = "handleGetBlock")
    public Depart getById(@PathVariable Integer id){
            return service.getById(id);
    }


//    public Depart handleGet(Integer id,Throwable e){
//        Depart depart = new Depart();
//        depart.setId(2).setName("sentinel degrade fallback !!");
//        return depart;
//    }
    public Depart handleGetBlock(Integer id, BlockException exception){
        Depart depart = new Depart();
        depart.setId(2).setName("sentinel flow fallback !!");
        return depart;
    }

    @GetMapping("/")
    @SentinelResource(value = "listHandle" , blockHandler = "handleListBlock")
    public List<Depart> getList(){
        return service.getList();
    }

    public List<Depart> handleListBlock(BlockException e) {
        ArrayList<Depart> departs = new ArrayList<>();
        Depart depart = new Depart();
        depart.setId(2).setName("list sentinel flow fallback !!");
        departs.add(depart);
        return departs;
    }



    @GetMapping("/param")
    @SentinelResource(value = "paramHandle",blockHandler = "paramHandleBlock")
    public String getParam(Integer id,String name){
        return "params "+id+":"+name;
    }


    //    public Depart handleGet(Integer id,Throwable e){
//        Depart depart = new Depart();
//        depart.setId(2).setName("sentinel degrade fallback !!");
//        return depart;
//    }
    public String paramHandleBlock(Integer id,String name, BlockException exception){
        return "sentinel fallback"+id+":"+name;
    }

}
