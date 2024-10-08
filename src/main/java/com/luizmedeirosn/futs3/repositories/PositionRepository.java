package com.luizmedeirosn.futs3.repositories;

import com.luizmedeirosn.futs3.entities.Position;
import com.luizmedeirosn.futs3.projections.postition.PositionParametersProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  @Query(
      nativeQuery = true,
      value =
          """
            SELECT
              pos.id,
              pos.name,
              pos.description,
              param.id AS parameterId,
              param.name AS parameterName,
              posparam.weight AS positionWeight
            FROM
              tb_position AS pos
                LEFT JOIN tb_position_parameter AS posparam ON posparam.position_id = pos.id
                LEFT JOIN tb_parameter AS param ON posparam.parameter_id = param.id
            WHERE
              pos.id = :id
            ORDER BY parameterName;
          """)
  List<PositionParametersProjection> customFindById(@Param(value = "id") Long id);

  @Modifying
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  @Query(
      nativeQuery = true,
      value =
          """
            DELETE FROM tb_position_parameter WHERE position_id = :id ;
            DELETE FROM tb_position WHERE id = :id ;
          """)
  void customDeleteById(@Param(value = "id") Long id);
}
