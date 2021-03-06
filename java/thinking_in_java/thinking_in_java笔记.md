### 190202

63-85

* java中几乎一切都是对象，且只能通过引用操纵对象。
* java中只有基本类型不是对象，基本类型变量直接包含值，而不是引用。
* 基本类型变量和引用保存在栈中，对象保存在堆中。
* SE5起，自动装箱和拆箱是全自动的。
* BigInteger和BigDecimal适用于商业计算和科学计算，特点是精度高，任意数值（极端大和极端小都行）但运算效率低。
* 方法可以描述该对象可以接受怎样的消息，调用对象上的方法也可以看作是向该对象发送消息的过程，方法体可以看作是对象收到请求后的行为。
* 继承有两种关系，is-a和is-like-a，is-a表示子类仅覆盖父类方法，没有扩展父类接口；is-like-a表示子类有自己的专属方法，扩展了父类接口。
* 组合和继承都可以实现代码复用，但组合更灵活，应优先考虑。
* 每个类都应该至少要有一个接口，广义上说，一个类的接口就是该类的所有公共方法（行为）的声明。
* 所有java代码都在干三件事：定义类型，定义对象，向对象发送消息。
* 字段一定会初始化，自动或手动。
* 方法参数，基本类型传值，其他都是传引用。
* 务必通过类名访问静态成员。
* 输出系统当前所有环境变量`System.getProperties().list(System.out);`。
* 文档注解有两种，一种是只能放行首的，另一种是可以放任意地方，但要用花括号围起来。注释文档可以注释公共或受保护的类、字段和方法。
* 所有HTML标签，除了`<hx>`，都可以在注释文档中使用。
* 对于对象中的不可变字段，可以声明为`public final`。

### 190223

86-97

* `new Random()`以当前时间作为种子，种子一样的随机数生成器生成的随机数序列一定是一样的。
* 自增自减操作符的前置和后置问题，其实只要将其考虑为一个普通的方法就不容易混淆了，这个方法一定会执行自增或自减操作，前置和后置的区别其实就是返回值的区别：
  * 前置：返回的是**新值**。
  * 后置：返回的是**旧值**。
* 对于基本类型的包装类型，`==`和`!=`比较的还是引用，不是其中包装的数值。
* float和double永远不要比相等，永远不要比大小。
* 整数加前缀0x表示十六进制字面值，加0表示八进制字面值，加0b表示无符号二进制字面值，二进制字面值数位之间可以加下划线增强可读性；后缀加L表示长整型，后缀L能不加就不加吧，一般都是多余的。
* 浮点数加后缀f表示类型是float，加后缀d表示类型是double。
* Integer和Long类中有一个比较方便的静态方法toBinaryString(int or long value)，可以获取任意整数的二进制字符串，toHexString()等等同理。
* 科学计数法，用到不多，字面值像这样：`1.39e-43f`，表示1.39乘以**10**的负43次方，类型是float，默认是double。在一些科学计算中出现一些极端大小的数值才会用到吧。

### 190227

98-103

* Boolean类型有三个值，true, false和null，在布尔表达式中null视为false。
* `~`是位运算符，取反。
* `>>`对正数操作，高位补0；对负数操作，高位补1。
* `>>>`做右位移操作，不管正负数，高位都补0。
* 对char, byte和short位移操作，**将以十进制值自动转型为int**，输出结果也是int；位移long，得到的结果也是long。这一点虽然很细节，但是不知道的话就容易踩坑，举个栗子：
  ```
  byte a = 0xff;
  a >> 1; // 输出0xffffffff，因为a的值是-1:byte，自动转成-1:int，十六进制就是0xffffffff（补码），再右移一位，还是0xffffffff
  a >>>= 1; // a的值为0xff，首先执行a >>> 1得到结果0x7fffffff，这个值赋给a的时候高位包括符号位全部舍弃，就是0xff
  ```
  数位操作比较迷的话最好还是事先在groovysh或jshell里面试验一下吧。