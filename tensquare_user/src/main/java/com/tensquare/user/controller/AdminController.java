package com.tensquare.user.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * admin控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Resource
	private JwtUtil jwtUtil;

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",adminService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",adminService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Admin>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",adminService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param admin
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Admin admin  ){
		adminService.add(admin);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param admin
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Admin admin, @PathVariable String id ){
		admin.setId(id);
		adminService.update(admin);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id){
		try {
			String authHeader = httpServletRequest.getHeader("Authorization");//获取头信息
			if (authHeader == null || "".equals(authHeader)) {
				return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
			}
			//前后端约定:前端请求微服务时需要添加头信息Authorization ,内容为Admin+空格 +token
			if (!authHeader.startsWith("Admin ")) {
				return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
			}
			String token = authHeader.substring(6);//提取空格后面的token
			Claims claims = jwtUtil.parseJWT(token);
			String roles = (String) claims.get("roles");
			if (claims == null || !roles.equals("admin")) {
				return new Result(false, StatusCode.ACCESS_ERROR, "权限不足");
			}
			adminService.deleteById(id);
			return new Result(true, StatusCode.OK, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, StatusCode.ERROR, "删除失败");
		}
	}

	/**
	 * 管理员登录密码效验
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Result login(@RequestBody Map<String,String> loginMap){
		String mobile = loginMap.get("mobile");
		String password = loginMap.get("password");
		Admin admin = adminService.findByLoginnameAndPassword(mobile, password);
		if (admin!=null){
			//生成token ,并设置返回
			String token= jwtUtil.createJWT(admin.getId(),admin.getLoginname(),"admin");//这里的角色可以通过springSecurity获取
			Map map = new HashMap<>();
			map.put("token",token);
			map.put("name",admin.getLoginname());//登录名
			map.put("role","admin");//角色
			return new Result(true,StatusCode.OK,"登录成功",map);
		}else {
			return new Result(false,StatusCode.LOGIN_ERROR,"用户名或密码错误");
		}
	}



}
