package dea.eelkedejong.spotitube.playlist.exceptions;

public class NotOwnerOfPlaylistException extends RuntimeException {
    public NotOwnerOfPlaylistException(String message) {
        super(message);
    }
}
