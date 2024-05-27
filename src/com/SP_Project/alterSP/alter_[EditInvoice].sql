USE [DOAN_PHANBON]
GO
/****** Object:  StoredProcedure [dbo].[EditInvoice]    Script Date: 5/27/2024 9:26:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[EditInvoice]
    @CustomerId INT,
    @StaffId INT,
    @InvoiceId INT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Invoices
    SET 
        CustomerId = @CustomerId,
        StaffId = @StaffId,
		UpdateDate = GETDATE()
    WHERE
        InvoiceId = @InvoiceId;
END
