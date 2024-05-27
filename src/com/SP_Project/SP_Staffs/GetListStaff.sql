CREATE PROCEDURE FindListStaff
    @Name NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @SearchTerm NVARCHAR(100) = '%' + @Name + '%';

    SELECT 
        StaffId, 
        Name, 
        Age, 
        Email, 
        Address, 
        Username, 
        Password, 
        AccountPrevilege 
    FROM 
        Staffs 
    WHERE 
        Name LIKE @SearchTerm;
END


