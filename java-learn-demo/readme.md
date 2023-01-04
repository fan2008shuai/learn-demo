# 流
- 集合关注的是数据，流关注的是计算
- filter会对流中的数据**逐个计算**，但是如果流很大且有排序特性（如升序），那么可以通过takeWhile或者dropWhile的方式**提前结束**。
- anyMatch, allMatch, noneMatch分别是只要有一个符合，必须全部符合，必须都不符合；他们的返回值是boolean。
- findAny对于串行的程序，在流中发现第一个就会结束流。findFirst也是。但是，如果在并行程序中，findAny的限制更少，执行更快。