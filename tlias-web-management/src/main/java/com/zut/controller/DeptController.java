package com.zut.controller;

import com.zut.anno.Log;
import com.zut.pojo.Dept;
import com.zut.pojo.Result;
import com.zut.service.DeptService;
import com.zut.service.impl.DeptServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
/*
返回统一的响应结果
 */

/*@RestController
public class DeptController {*/
/*//    查询部门
// @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() throws Exception{
//  1.加载读写dept.txt
//       相对路径  格式固定记住
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("dept.txt");
//         引入commons-io依赖   一行一行读取
       List<String> lines=  IOUtils.readLines(inputStream,"UTF-8");

//2.解析文本，封装为对象 将多个对象封装到集合中

        //需要将得到的对象  进一步操作不用foreach 用map集合
        List<Dept> deptList = lines.stream().map(line -> {
            String[] parts = line.split(",");//以“，”为分割点将一行数据存在数组中
            int id = Integer.parseInt(parts[0]);  //将字符1 转化成数字

            String name = parts[1]; //部门名

            LocalDateTime updateTime = LocalDateTime.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));//日期
//               返回对象集合
            return new  Dept(id,name,updateTime);

        }).toList();//将单行数据构成的对象集合 封装到一个大集合中

//        3.响应数据json格式
//        会自动转化为json格式
        return Result.success(deptList);

    }*/

   @RequestMapping("/depts")
    @RestController
   @Slf4j
    public class DeptController {

    //    查询部门
    //用接口名定义，后面new具体使用的实现类，面向接口编程多态
    @Autowired //依赖注入注解，会自动到ioc容器中找该类型对象,并赋值该成员变量
    private DeptService deptService;  // = new DeptServiceImpl();

// @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping()
    public Result list() throws Exception{
        //1.调用service 拿到处理后的数据
        List<Dept> deptList = deptService.list();

//        3.响应数据json格式 因为加了RestController注解会自动转化为json格式
        return Result.success(deptList);

    }

    //删除部门 1.原始httpRequest对象转换
     /*   @DeleteMapping("/depts")
        public Result delete(HttpServletRequest request){
            String id = request.getParameter("id");
            int _id = Integer.parseInt(id);
            System.out.println("根据id删除部门： "+_id);
             return Result.success();
        }*/
   //2.spring注解
   /* @DeleteMapping("/depts")
    public Result delete(@RequestParam(name = "id") Integer _id){
        System.out.println("根据id删除部门： "+_id);
        return Result.success();
    }*/

    //3.    2.的省略版  当前端传递参数名和方法形参一致
        @Log
        @DeleteMapping()
        public Result delete( Integer id) {
            log.info("根据id删除部门： " + id);
            deptService.delete(id);
            return Result.success();
        }

        /*新增部门
          在controller中对象形式接受json参数
        */
         @Log
         @PostMapping()
        public Result add(@RequestBody Dept dept){
             log.info("添加部门："+dept);
             deptService.add(dept);
            return Result.success();
         }

         //id 查询部门
         @GetMapping("/{id}")
         public Result getById(@PathVariable Integer id){
             log.info("根据id修改部门："+id);
            Dept dept =  deptService.getById(id);
            return Result.success(dept);
         }
//         修改部门  接受前端参数请求  json dept对象形式。
         @Log
        @PutMapping()
         public Result update(@RequestBody Dept dept){
            log.info("修改部门："+dept);
            deptService.update(dept);
            return Result.success();
        }

}
