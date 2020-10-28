package com.uniba.corpusmanager.service.mapper;


import com.uniba.corpusmanager.domain.ActionLog;
import com.uniba.corpusmanager.service.dto.ActionLogDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link ActionLog} and its DTO {@link ActionLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActionLogMapper extends EntityMapper<ActionLogDTO, ActionLog> {


    default ActionLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        ActionLog actionLog = new ActionLog();
        actionLog.setId(id);
        return actionLog;
    }
}
