package com.luizmedeirosn.futs3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luizmedeirosn.futs3.entities.PositionParameter;
import com.luizmedeirosn.futs3.projections.postition.PositionParametersProjection;

public interface PositionParameterRepository extends JpaRepository<PositionParameter, Long> {

    @Query(
        nativeQuery = true,
        value = """
            SELECT
                param.id AS parameterId,
                param.name AS parameterName,
                posparam.weight AS parameterWeight
            FROM
                tb_position_parameter as posparam
                    INNER JOIN tb_position AS pos
                        ON posparam.position_id = pos.id
                    INNER JOIN tb_parameter AS param
                        ON posparam.parameter_id = param.id
            WHERE
                pos.id = :positionId\\;
        """
    ) List<PositionParametersProjection> findAllPositionParameters(Long positionId);

}
