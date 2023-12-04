package ebe.P_Judakov.s.JAVABOT.repository.mysql;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.SubscribedChannel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
public class SubscribedChannelRepository implements ebe.P_Judakov.s.JAVABOT.repository.interfaces.SubscribedChannelRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void flush() {
    }

    @Override
    public <S extends SubscribedChannel> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends SubscribedChannel> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<SubscribedChannel> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public SubscribedChannel getOne(Long aLong) {
        return null;
    }

    @Override
    public SubscribedChannel getById(Long aLong) {
        return null;
    }

    @Override
    public SubscribedChannel getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends SubscribedChannel> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends SubscribedChannel> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends SubscribedChannel> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends SubscribedChannel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends SubscribedChannel> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends SubscribedChannel> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends SubscribedChannel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


    @Override
    public <S extends SubscribedChannel> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<SubscribedChannel> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<SubscribedChannel> findAll() {
        return null;
    }

    @Override
    public List<SubscribedChannel> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(SubscribedChannel entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            SubscribedChannel managedEntity = entityManager.find(SubscribedChannel.class, entity.getId());
            if (managedEntity != null) {
                entityManager.remove(managedEntity);
            }
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        for (Long id : longs) {
            SubscribedChannel entity = entityManager.find(SubscribedChannel.class, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
        }
    }

    @Override
    public void deleteAll(Iterable<? extends SubscribedChannel> entities) {
        for (SubscribedChannel entity : entities) {
            delete(entity);
        }
    }


    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM SubscribedChannel").executeUpdate();
    }

    @Override
    public List<SubscribedChannel> findAll(Sort sort) {
        return entityManager.createQuery("SELECT s FROM SubscribedChannel s", SubscribedChannel.class)
                .getResultList();
    }

    @Override
    public Page<SubscribedChannel> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<SubscribedChannel> findByChatId(Long chatId) {
        return entityManager.createQuery("SELECT s FROM SubscribedChannel s WHERE s.chatId = :chatId", SubscribedChannel.class)
                .setParameter("chatId", chatId)
                .getResultList();
    }
    @Override
    public SubscribedChannel save(SubscribedChannel subscribedChannel) {
        entityManager.persist(subscribedChannel);
        return subscribedChannel;
    }

}

