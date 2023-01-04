sharding-sphere微内核模式，采用spi的方式实现插件化。

基本逻辑：
xxxFactory通过getInstance从spi中获取具体实例。

例如：
```java
StandalonePersistRepository repository = StandalonePersistRepositoryFactory.getInstance(persistRepositoryConfiguration);

public static StandalonePersistRepository getInstance(final PersistRepositoryConfiguration config) {
    return null == config 
        ? RequiredSPIRegistry.getRegisteredService(StandalonePersistRepository.class)
        : TypedSPIRegistry.getRegisteredService(StandalonePersistRepository.class, config.getType(), config.getProps());
}

```