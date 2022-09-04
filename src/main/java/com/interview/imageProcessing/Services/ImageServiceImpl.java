package com.interview.imageProcessing.Services;

import com.interview.imageProcessing.Models.ImageResponseModel;
import com.interview.imageProcessing.Models.ReceveImageModel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;


@Service
public class ImageServiceImpl implements IImageService{

    @Override
    public ImageResponseModel editImage(MultipartFile originalImage, Boolean grayScale,Boolean compress) throws IOException {
        if(saveFile(originalImage)){
            if (grayScale){
                changeGrayScale(originalImage.getOriginalFilename());
            }
            if (compress){
                compressImage(originalImage.getOriginalFilename());
            }
        }
        ImageResponseModel imageResponseModel=new ImageResponseModel();
        imageResponseModel.setImage(originalImage.getOriginalFilename());
        imageResponseModel.setMessage("Image Edited");
        imageResponseModel.setStatus(true);
        return imageResponseModel;
    }

    private void changeGrayScale(String filename){
        BufferedImage  image;
        int width;
        int height;
        try {
            File input = new File("./images/" + filename);
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            for(int i=0; i<height; i++) {

                for(int j=0; j<width; j++) {

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() *0.114);
                    Color newColor = new Color(red+green+blue,

                            red+green+blue,red+green+blue);

                    image.setRGB(j,i,newColor.getRGB());
                }
            }

            File ouptut = new File("./images/" + filename);
            ImageIO.write(image, "jpg", ouptut);

        } catch (Exception e) {}
    }

    private void compressImage(String filename){
        try{
            File input = new File("./images/" + filename);
            BufferedImage image = ImageIO.read(input);

            File compressedImageFile = new File("./images/" + filename);
            OutputStream os =new FileOutputStream(compressedImageFile);

            Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = (ImageWriter) writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.05f);
            writer.write(null, new IIOImage(image, null, null), param);

            os.close();
            ios.close();
            writer.dispose();

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
    }

    private Boolean saveFile(MultipartFile file) throws IOException {
       try {
           String name=file.getOriginalFilename();
           System.out.println(name);
           File saveFile = new File("./images/" + name);
           try {
               saveFile.createNewFile();
               FileOutputStream fOut = new FileOutputStream(saveFile);
               fOut.write(file.getBytes());
               fOut.close();
           }catch (Exception e){
               File folder = new File("./images");
               folder.mkdir();
               saveFile.createNewFile();
               FileOutputStream fOut = new FileOutputStream(saveFile);
               fOut.write(file.getBytes());
               fOut.close();
           }
           return true;
       }catch (Exception e){
           return false;
       }

    }
}
