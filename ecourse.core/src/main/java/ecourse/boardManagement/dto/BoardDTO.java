package ecourse.boardManagement.dto;

import eapli.framework.representations.dto.DTO;
import ecourse.boardManagement.domain.BoardStatusEnum;

import java.util.List;

@DTO
public class BoardDTO {
    public String title;
    public String owner;
    public int numRows;
    public int numColumns;
    public List<String> participants;
    public BoardStatusEnum state;
}