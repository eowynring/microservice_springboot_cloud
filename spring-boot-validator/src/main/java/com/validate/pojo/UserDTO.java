package com.validate.pojo;

import com.validate.custom.EncryptId;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author guofei
 * @date 2022/8/2 10:28 AM
 */
@Data
public class UserDTO {

  /*@EncryptId
  private String encryptId;*/

  //@Min(value = 100L, groups = Update.class)
  //@Min(value = 100L, groups = Save.class)
  @Min(value = 100L)
  private Long userId;

  //@NotNull(groups = {Save.class, Update.class})
  @NotNull
  //@Length(min = 2, max = 10, groups = {Save.class, Update.class})
  private String userName;

  /*@NotNull(groups = {Save.class, Update.class})
  @Length(min = 6, max = 20, groups = {Save.class, Update.class})
  private String account;

  @NotNull(groups = {Save.class, Update.class})
  @Length(min = 6, max = 20, groups = {Save.class, Update.class})
  private String password;
*/
  /**
   * 嵌套集合校验会对集合里面的每一项都进行校验， 例如List<Job>字段会对这个list里面的每一个Job对象都进行校验
   */
  //@NotNull(groups = {Save.class, Update.class})
  //@Valid
  //private Job job;


  @Data
  public static class Job {

    @Min(value = 1, groups = Update.class)
    private Long jobId;

    @NotNull(groups = {Save.class, Update.class})
    @Length(min = 2, max = 10, groups = {Save.class, Update.class})
    private String jobName;

    @NotNull(groups = {Save.class, Update.class})
    @Length(min = 2, max = 10, groups = {Save.class, Update.class})
    private String position;
  }

  //注意:在声明分组的时候尽量加上 extend javax.validation.groups.Default 否则,在你声明@Validated(Update.class)
  //的时候,就会出现你在默认没添加groups = {}的时候的校验组@Email(message = "邮箱格式不对"),会不去校验,
  // 因为默认的校验组是groups = {Default.class}.

  /**
   * 保存的时候校验分组
   */
  public interface Save extends Default {

  }

  /**
   * 更新的时候校验分组
   */
  public interface Update extends Default {

  }
}
