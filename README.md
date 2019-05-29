# SpringBoot2
SpringBoot高级应用
1. Spring原生缓存
    -   @CacheAable：在方法执行之前判断，缓存中是否有值，有则不执行方法了
    -   @CachePut：方法先执行，再将方法的返回结果放到缓存中（方法没出异常），方法必然会执行
    -   @CacheEvict：默认在方法执行之后（方法没出异常），清除指定缓存
