package org.fk.core.tsid;

import io.hypersistence.tsid.TSID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TsidUtils {

    public static final Logger LOGGER = Logger.getLogger(TsidUtils.class);

    @ConfigProperty(name = "tsid.node.count")
    Integer nodeCount;

    @ConfigProperty(name = "tsid.node.id")
    Integer nodeId;

    @Produces
    public TSID.Factory getFactory() {
        int nodeBits = (int) (Math.log(nodeCount) / Math.log(2));
        return TSID.Factory.builder()
                .withRandomFunction(TSID.Factory.THREAD_LOCAL_RANDOM_FUNCTION)
                .withNodeBits(nodeBits)
                .withNode(nodeId)
                .build();
    }
}
