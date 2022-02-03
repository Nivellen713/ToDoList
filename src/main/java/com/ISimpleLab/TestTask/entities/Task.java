package com.ISimpleLab.TestTask.entities;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="Deadline" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Complete" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="caption" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "description",
        "priority",
        "deadline",
        "status",
        "complete"
})
public class Task {

    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Priority")
    protected byte priority;
    @XmlElement(name = "Deadline", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deadline;
    @XmlElement(name = "Status", required = true)
    protected String status;
    @XmlElement(name = "Complete")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar complete;
    @XmlAttribute(name = "id")
    protected Integer id;
    @XmlAttribute(name = "caption")
    protected String caption;

    public Task(){

    }

    public Task(String caption, String description, byte priority, XMLGregorianCalendar deadline){
        this.caption = caption;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = "new";
    }

    /**
     * Gets the value of the description property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the priority property.
     *
     */
    public byte getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     *
     */
    public void setPriority(byte value) {
        this.priority = value;
    }

    /**
     * Gets the value of the deadline property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDeadline() {
        return deadline;
    }

    /**
     * Sets the value of the deadline property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDeadline(XMLGregorianCalendar value) {
        this.deadline = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the complete property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getComplete() {
        return complete;
    }

    /**
     * Sets the value of the complete property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setComplete(XMLGregorianCalendar value) {
        this.complete = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the caption property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the value of the caption property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCaption(String value) {
        this.caption = value;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", priority=" + priority +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", complete=" + complete +
                ", id=" + id +
                ", caption='" + caption + '\'' +
                '}';
    }
}
