
package PEJ.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="Product", url="${api.url.delivery}")
public interface ProductService {

    @RequestMapping(method= RequestMethod.POST, path="/cancellations2")
    public void changePrdAttrCd(@RequestBody Product product);

}