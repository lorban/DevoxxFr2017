package org.ehcache.service;

import org.ehcache.repository.SomeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Example service : Cache through
 *
 * Please implement TODO lines
 *
 */

@Service
public class Ex4Service implements SomeService {

  private static final Logger LOGGER = LoggerFactory.getLogger("org.jsoftbiz.Demo");

  private SomeRepository repository = new SomeRepository();
  private Cache<String, String> cache;

  public Ex4Service() {
    CachingProvider provider = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
    CacheManager cacheManager = provider.getCacheManager();

    MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
    configuration.setTypes(String.class, String.class);
    configuration.setCacheLoaderFactory(new FactoryBuilder.ClassFactory<>("org.jsoftbiz.service.SomeCacheLoader"));
    configuration.setReadThrough(true);
    cache = cacheManager.createCache("someCache4", configuration);
  }

  @Override
  public String someLogic(final String id) {
    LOGGER.debug("---> Call to service 4");

    // TODO : Get the value from the cache directly instead
    String val = cache.get(id);
    return val;
  }
}