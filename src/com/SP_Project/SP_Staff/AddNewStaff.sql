CREATE PROCEDURE AddStaff
    @Name NVARCHAR(100),
    @Age INT,
    @Email NVARCHAR(100),
    @Address NVARCHAR(255),
    @Username NVARCHAR(100),
    @Password NVARCHAR(100),
    @AccountPrevilege INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        INSERT INTO Staffs (Name, Age, Email, Address, Username, Password, AccountPrevilege)
        VALUES (@Name, @Age, @Email, @Address, @Username, @Password, @AccountPrevilege);
    END TRY
    BEGIN CATCH
        -- Xử lý ngoại lệ (ví dụ: in ra thông báo lỗi)
        PRINT ERROR_MESSAGE();
    END CATCH
END
