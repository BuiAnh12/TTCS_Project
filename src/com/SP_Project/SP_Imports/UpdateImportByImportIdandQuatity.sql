
CREATE PROCEDURE UpdateImportByImportIdandQuatity
    @Increment INT,
    @ImportId INT
AS
BEGIN
	SET NOCOUNT ON	
    UPDATE Imports
    SET AvailableQuantity = AvailableQuantity + @Increment
    WHERE ImportId = @ImportId;
END;
