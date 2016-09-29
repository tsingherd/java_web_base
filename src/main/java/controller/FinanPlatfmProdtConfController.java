package controller;

import model.FinancingPlatformProductsConfig;
import model.PolicyProducts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LGG on 2016/9/29.
 */
@Controller
@RequestMapping("/financingplatform")
public class FinanPlatfmProdtConfController {

    /**
     * 用于保存表单数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getAddView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/add");
        Map<String,Object> map = new HashMap<>();
        FinancingPlatformProductsConfig financingPlatformProductsConfig = new FinancingPlatformProductsConfig();
        PolicyProducts policyProducts = new PolicyProducts();
        try{
            if(request.getParameter("creditModel")!= null && !request.getParameter("creditModel").isEmpty() ) {
                policyProducts.setCreditModel(request.getParameter("creditModel"));
                financingPlatformProductsConfig.setPolicyProducts(policyProducts);
            }
            map.put("success",false);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> saveFinancingPlatFormConfig(HttpServletRequest request) {
//        Map<String,Object> map = new HashMap<>();
//        FinancingPlatformProductsConfig financingPlatformProductsConfig = new FinancingPlatformProductsConfig();
//        PolicyProducts policyProducts = new PolicyProducts();
//        try{
//            if(request.getParameter("creditModel")!= null && !request.getParameter("creditModel").isEmpty() ) {
//                policyProducts.setCreditModel(request.getParameter("creditModel"));
//                financingPlatformProductsConfig.setPolicyProducts(policyProducts);
//            }
//                map.put("success",false);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  map;
//    }

//    @RequestMapping(value = "/show", method = RequestMethod.GET)
//    public ModelAndView showFinancingPlatFormConfig(HttpServletRequest request) {
//        ModelAndView model = new ModelAndView("/service_unit");
//        List<ServiceUnitAssDTO> serviceUnitAssDTOList = serviceUnitService.getServiceOverView();
//        Long serviceTotalNum = serviceUnitService.getServiceTotalNum();
//        Integer servicePageTotalNum =(int) Math.ceil(((double) serviceTotalNum)/ Pagination.PAGE_LENGTH);
//        model.addObject("serviceUnitAssDTOList",convertServiceUnitAssDTO(serviceUnitAssDTOList));
//        model.addObject("maxPageNum",servicePageTotalNum);
//        model.addObject("totalCounts",serviceTotalNum);
//        return model;
//    }
}
