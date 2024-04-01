package example;

public class ExceptionTest {

    @Test(expected = StorageException.class)
    public void retrieveSectionShouldThrowOnInvalidFileName() {
        sectionStore.retrieveSection("invalid - file");
    }
}
