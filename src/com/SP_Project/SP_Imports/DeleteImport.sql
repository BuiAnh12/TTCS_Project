CREATE PROCEDURE DeleteImport
    @ImportId INT
AS
BEGIN
    SET NOCOUNT ON
    DELETE FROM Imports
    WHERE ImportId = @ImportId;
END;
