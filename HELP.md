# Caching in Spring Boot Example [Student Management Application + Caching + REST API]

## **Cache**
Cache is any temporary storage location that lies between the application and persistence database or a third-party application that stores the most frequently or recently accessed data so that future requests for that data can be served faster.

There are mainly 4 types of Caching :

1. CDN Caching
2. Database Caching
3. **In-Memory Caching**
4. Web server Caching
   Note: Depend on interest , i will make separate video of other 3 caching mechanism

## **Caching Annotations **
### 1. @EnableCaching
- Add the `spring-boot-starter-cache`  dependency to your `pom.xml` .
- Enable caching in your main application class or a configuration class using the `@EnableCaching`  annotation.
### 2. @Cacheable
- `@Cacheable` : Marks a method whose result should be cached. If the method is called again with the same arguments, the cached value is returned without executing the method. You specify a cache name (e.g., `@Cacheable("myCache")` ) and optionally a key (e.g., `@Cacheable(value="myCache", key="#id")` ). `@Cacheable(value="myCache", key="{#id1, #id2}")`
### 3. @CachePut
- `@CachePut` : Ensures that a method is always executed, and its result is then placed or updated in the cache. Useful for updating cache entries after data modification.
### 4. @CacheEvict
- `@CacheEvict` : Triggers the removal of one or more entries from the cache. You can specify `allEntries=true`  to clear the entire cache or target specific entries using the `key`  attribute.
  **@Cacheable** skips the method execution while the **@CachePut** runs the method and puts the result into the cache

## Cache Providers
- **In-Memory Caching (Default):** Spring Boot provides a simple in-memory cache using `ConcurrentHashMap`  by default when no other cache provider is configured. This is suitable for basic scenarios but is not persistent and is limited to the application's memory.
- **External Cache Providers**: For more robust and scalable caching, integrate with external solutions like:
    - Redis: A popular in-memory data store often used for distributed caching.
    - Caffeine: A high-performance, near-optimal local caching library.
    - Ehcache: A widely used open-source cache.
    - Hazelcast: A distributed in-memory data grid.

## Internal Working of Caching
```
ConcurrentMapCacheManager
```
```
CacheManager
```
```
Cache
```
```
ConcurrentMapCache
```



