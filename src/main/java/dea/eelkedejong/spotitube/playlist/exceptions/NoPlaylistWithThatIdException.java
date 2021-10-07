package dea.eelkedejong.spotitube.playlist.exceptions;

public class NoPlaylistWithThatIdException extends RuntimeException {
    public NoPlaylistWithThatIdException(String message) {
        super(message);
    }
}
