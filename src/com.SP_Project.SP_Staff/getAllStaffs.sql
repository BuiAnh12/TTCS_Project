CREATE PROCEDURE GetAllStaff
    @Status INT,
    @Name NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Query NVARCHAR(MAX);

    IF @Status = 1
    BEGIN
        SET @Query = 'SELECT * FROM Staffs WHERE Name LIKE ''%'' + @Name + ''%'' ORDER BY Name;';
    END
    ELSE IF @Status = 2
    BEGIN
        SET @Query = 'SELECT * FROM Staffs WHERE Name LIKE ''%'' + @Name + ''%'' ORDER BY Email;';
    END
    ELSE IF @Status = 3
    BEGIN
        SET @Query = 'SELECT * FROM Staffs WHERE Name LIKE ''%'' + @Name + ''%'' ORDER BY AccountPrevilege;';
    END

    EXEC sp_executesql @Query, N'@Name NVARCHAR(100)', @Name;
END


