//
//  KeyboardViewController.swift
//  SmKeyboard
//
//  Created by Developer on 4/9/17.
//  Copyright (c) 2017 Developer. All rights reserved.
//

import UIKit
import MobileCoreServices

extension UIColor{
    func HexToColor(hexString: String, alpha:CGFloat? = 1.0) -> UIColor {
        // Convert hex string to an integer
        let hexint = Int(self.intFromHexString(hexString))
        let red = CGFloat((hexint & 0xff0000) >> 16) / 255.0
        let green = CGFloat((hexint & 0xff00) >> 8) / 255.0
        let blue = CGFloat((hexint & 0xff) >> 0) / 255.0
        let alpha = alpha!
        // Create color object, specifying alpha as well
        let color = UIColor(red: red, green: green, blue: blue, alpha: alpha)
        return color
    }
    
    func intFromHexString(hexStr: String) -> UInt32 {
        var hexInt: UInt32 = 0
        // Create scanner
        let scanner: NSScanner = NSScanner(string: hexStr)
        // Tell scanner to skip the # character
        scanner.charactersToBeSkipped = NSCharacterSet(charactersInString: "#")
        // Scan hex value
        scanner.scanHexInt(&hexInt)
        return hexInt
    }
}

class KeyboardViewController: UIInputViewController {
    var heightConstraint: NSLayoutConstraint?
    var viewMainKeyboard: UIView!
    var xibSmiliesLayout: UIView!
    var xibFlowerLayout: UIView!
    var xibFoodLayout: UIView!
    var xibOtherLayout: UIView!
    
    @IBOutlet weak var mainKeyLayoutContainer: UIView!
    @IBOutlet weak var mainKeyCtrlLayoutContainer: UIView!
    
    var totalBtn: Int = 0
    
    enum onScreenKeyboard {
        case SmiliesKeyboard
        case FlowerKeyboard
        case FoodKeyboard
        case OthersKeyboard
    }
    struct onColorSet {
        var KeyBodyPanel = "#e8e9ed"
        var KeyControlPanel = "#f8f8f8"
        var KeyCtrlBtnDefault = "#f8f8f8"
        var KeyCtrlBtnSelected = "#e8e9ed"
        var KeyboardKeyDefault = "#e8e9ed"
    }
    var onKeyboardColorSet = onColorSet()
    var onScreenDefaultKeyboard : onScreenKeyboard = onScreenKeyboard.SmiliesKeyboard
    
    override func updateViewConstraints() {
        super.updateViewConstraints()
        
        // Add custom view sizing constraints here
    }
    
    func onLoadViewFromXibFile(nibNamed: String) -> UIView {
        var xibViews = NSBundle.mainBundle().loadNibNamed(nibNamed, owner: self, options: nil)
        var uiView = xibViews[0] as! UIView
        return uiView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //|--------------------|MAIN KEYBOARD CONTAINER ATTACHMENT WITH SELF VIEW
        //|--------------------|ViewMainKeyboardLayout
        viewMainKeyboard = onLoadViewFromXibFile("ViewMainKeyboardLayout")
        viewMainKeyboard.frame = self.view.bounds
        self.view.addSubview(viewMainKeyboard)
        //|--------------------|SMILIES KEYBOARD LAYOUT ATTACHED WITH MAIN KEYBOARD LAYOUT CONTAINER
        onAttachedSmiliesKeyboard()
        //        var subViews = xibSmiliesLayout.subviews
        //        println("COUNT_SUBVIEW: \(xibSmiliesLayout.subviews.count)")
        //        //for view in self.view.subviews as [UIView]
        //        //for subview : AnyObject in subviews{
        //        for view in subViews as! [UIView] {
        //            var subButton = view.subviews
        //            for vBtn in subButton as! [UIButton] {
        ////                if let btn = vBtn as? UIButton {
        ////                    //
        ////                }
        //                totalBtn++
        //            }
        //        }
        //
        //|--------------------|
        onSetBottomControlPanel()
        //|--------------------|
    }
    
    func onSetBottomControlPanel()
    {
        //mainKeyCtrlLayoutContainer.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        mainKeyCtrlLayoutContainer.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyCtrlBtnDefault, alpha: 1.0)
        var btnCount = 0
        for vBtn in self.mainKeyCtrlLayoutContainer.subviews as! [UIButton] {
            btnCount++
            if(btnCount == 2)
            {
                vBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyCtrlBtnSelected, alpha: 1.0)
            }
            else
            {
                vBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyCtrlBtnDefault, alpha: 1.0)
            }
        }
    }
    
    func onAttachedSmiliesKeyboard()
    {
        //|--------------------|SMILIES KEYBOARD LAYOUT ATTACHED WITH MAIN KEYBOARD LAYOUT CONTAINER
        //|--------------------|ViewSmiliesLayout
        self.xibSmiliesLayout = onLoadViewFromXibFile("ViewSmiliesLayout")
        xibSmiliesLayout.frame = self.mainKeyLayoutContainer.bounds
        self.mainKeyLayoutContainer.addSubview(xibSmiliesLayout)
        var uiScrollSmiliesView = xibSmiliesLayout as! ViewSmiliesLayout
        uiScrollSmiliesView.uiScrollView.contentSize.height = 178
        //|--------------------|
        xibSmiliesLayout.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        uiScrollSmiliesView.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        onGetSmiliesBtnLis(xibSmiliesLayout)
        //|--------------------|ViewAlphabetLayout
    }
    
    func onAttachedFlowerKeyboard() {
        //|--------------------|FLOWER KEYBOARD LAYOUT ATTACHED WITH MAIN KEYBOARD LAYOUT CONTAINER
        //|--------------------|ViewFlowerLayout
        self.xibFlowerLayout = onLoadViewFromXibFile("ViewFlowerLayout")
        xibFlowerLayout.frame = self.mainKeyLayoutContainer.bounds
        self.mainKeyLayoutContainer.addSubview(xibFlowerLayout)
        var uiScrollFlowerView = xibFlowerLayout as! ViewFlowerLayout
        uiScrollFlowerView.uiScrollView.contentSize.height = 178
        //|--------------------|
        xibFlowerLayout.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        uiScrollFlowerView.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        onGetFlowersBtnLis(xibFlowerLayout)
        //|--------------------|ViewAlphabetLayout
    }
    func onAttachedFoodKeyboard() {
        //|--------------------|FOOD KEYBOARD LAYOUT ATTACHED WITH MAIN KEYBOARD LAYOUT CONTAINER
        //|--------------------|ViewFoodLayout
        self.xibFoodLayout = onLoadViewFromXibFile("ViewFoodLayout")
        xibFoodLayout.frame = self.mainKeyLayoutContainer.bounds
        self.mainKeyLayoutContainer.addSubview(xibFoodLayout)
        var uiScrollFoodView = xibFoodLayout as! ViewFoodLayout
        uiScrollFoodView.uiScrollView.contentSize.height = 178
        //|--------------------|
        xibFoodLayout.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        uiScrollFoodView.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        onGetFoodsBtnLis(xibFoodLayout)
        //|--------------------|ViewAlphabetLayout
    }
    func onAttachedOthersKeyboard() {
        //|--------------------|OTHERS KEYBOARD LAYOUT ATTACHED WITH MAIN KEYBOARD LAYOUT CONTAINER
        //|--------------------|ViewFoodLayout
        self.xibOtherLayout = onLoadViewFromXibFile("ViewOthersLayout")
        xibOtherLayout.frame = self.mainKeyLayoutContainer.bounds
        self.mainKeyLayoutContainer.addSubview(xibOtherLayout)
        //        var uiScrollFoodView = xibOtherLayout as! ViewOthersLayout
        //        uiScrollFoodView.uiScrollView.contentSize.height = 178
        //|--------------------|
        xibOtherLayout.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        //        uiScrollFoodView.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
        onGetOthersBtnLis(xibOtherLayout)
        //|--------------------|ViewAlphabetLayout
    }
    
    func onGetSmiliesBtnLis(xibSmiliesView : UIView)
    {
        //var subViewsScroll = xibSmiliesView.subviews as! [UIScrollView]
        for subViewsScroll in xibSmiliesView.subviews as! [UIScrollView]
        {
            subViewsScroll.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
            for subViews in subViewsScroll.subviews {
                if let vBtn = subViews as? UIButton
                {
                    vBtn.addTarget(self, action: "btnPressedSmiliesKey:", forControlEvents: .TouchUpInside)
                }
                else
                {
                    var subViewOne: UIView = subViews as! UIView
                    subViewOne.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
                    for subViewsBtn in subViewOne.subviews {
                        if let vBtn = subViewsBtn as? UIButton {
                            vBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyboardKeyDefault, alpha: 1.0)
                            vBtn.addTarget(self, action: "btnPressedSmiliesKey:", forControlEvents: .TouchUpInside)
                        }
                    }
                }
            }
        }
        //        var subViewsOne = xibSmiliesView.subviews as! [UIScrollView]
        //        for vBtn in subViewsOne.subvi as! [UIButton]
        //        {
        //            vBtn.addTarget(self, action: "btnPressed:", forControlEvents: .TouchUpInside)
        //        }
    }
    func onGetFlowersBtnLis(xibFlowerView : UIView) {
        //var subViewsScroll = xibSmiliesView.subviews as! [UIScrollView]
        for subViewsScroll in xibFlowerView.subviews as! [UIScrollView] {
            subViewsScroll.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
            for subViews in subViewsScroll.subviews {
                if let vBtn = subViews as? UIButton
                {
                    vBtn.addTarget(self, action: "btnPressedFlowersKey:", forControlEvents: .TouchUpInside)
                }
                else
                {
                    var subViewOne: UIView = subViews as! UIView
                    subViewOne.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
                    for subViewsBtn in subViewOne.subviews {
                        if let vBtn = subViewsBtn as? UIButton {
                            vBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyboardKeyDefault, alpha: 1.0)
                            vBtn.addTarget(self, action: "btnPressedFlowersKey:", forControlEvents: .TouchUpInside)
                        }
                    }
                }
            }
        }
    }
    func onGetFoodsBtnLis(xibFoodsView : UIView) {
        //var subViewsScroll = xibSmiliesView.subviews as! [UIScrollView]
        for subViewsScroll in xibFoodsView.subviews as! [UIScrollView] {
            subViewsScroll.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
            for subViews in subViewsScroll.subviews {
                if let vBtn = subViews as? UIButton
                {
                    vBtn.addTarget(self, action: "btnPressedFoodsKey:", forControlEvents: .TouchUpInside)
                }
                else
                {
                    var subViewOne: UIView = subViews as! UIView
                    subViewOne.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
                    for subViewsBtn in subViewOne.subviews {
                        if let vBtn = subViewsBtn as? UIButton {
                            vBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyboardKeyDefault, alpha: 1.0)
                            vBtn.addTarget(self, action: "btnPressedFoodsKey:", forControlEvents: .TouchUpInside)
                        }
                    }
                }
            }
        }
    }
    func onGetOthersBtnLis(xibOtherLayout : UIView) {
        //var subViewsScroll = xibSmiliesView.subviews as! [UIScrollView]
        for subViewsScroll in xibOtherLayout.subviews as! [UIScrollView] {
            subViewsScroll.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
            for subViews in subViewsScroll.subviews {
                if let vBtn = subViews as? UIButton
                {
                    vBtn.addTarget(self, action: "btnPressedOthersKey:", forControlEvents: .TouchUpInside)
                }
                else
                {
                    var subViewOne: UIView = subViews as! UIView
                    subViewOne.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyBodyPanel, alpha: 1.0)
                    for subViewsBtn in subViewOne.subviews {
                        if let vBtn = subViewsBtn as? UIButton {
                            vBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyboardKeyDefault, alpha: 1.0)
                            vBtn.addTarget(self, action: "btnPressedOthersKey:", forControlEvents: .TouchUpInside)
                        }
                    }
                }
            }
        }
    }
    func btnPressedSmiliesKey(sender: AnyObject) {
        var btn = sender as! UIButton
        println("Pressed")
        let proxy = self.textDocumentProxy as! UIKeyInput
        //proxy.insertText(" Hi \u{1F496} \u{1F600}")
        //let c = Character(UnicodeScalar(65))
        //let s = String(UnicodeScalar(65))
        if(btn.tag > 0) {
            proxy.insertText(String(UnicodeScalar(btn.tag)))
        }
    }
    func btnPressedFlowersKey(sender: AnyObject) {
        var btn = sender as! UIButton
        let proxy = self.textDocumentProxy as! UIKeyInput
        //println("Pressed")
        //proxy.insertText(" FLOWER ")
        onPastBoardData(btn)
        //proxy.insertText(btn.currentImage!.description)
        //proxy.insertText(btn.restorationIdentifier!.fileSystemRepresentation())
        //var val: AnyObject? = btn.layer.valueForKey("keyPath")
        //var val: AnyObject? = btn.layer.valueForUndefinedKey("keyPath")
        //var val: AnyObject? = btn.layer.accessibilityHint
        //proxy.insertText("PP:- \(str)")
    }
    func btnPressedFoodsKey(sender: AnyObject) {
        var btn = sender as! UIButton
        let proxy = self.textDocumentProxy as! UIKeyInput
        //println("Pressed")
        //proxy.insertText(" FOOD ")
        onPastBoardData(btn)
    }
    func btnPressedOthersKey(sender: AnyObject) {
        var btn = sender as! UIButton
        let proxy = self.textDocumentProxy as! UIKeyInput
        //println("Pressed")
        //proxy.insertText(" FOOD ")
        onPastBoardData(btn)
    }
    func onPastBoardData(argButton: UIButton) {
        
        let pasteboard: UIPasteboard = UIPasteboard.generalPasteboard() //UIPasteboard.general
        //let imageName = argImageName
        var strIdentifier: String! = argButton.restorationIdentifier
        let newImage = UIImage(named: strIdentifier)
        let imgData = UIImagePNGRepresentation(newImage)
        
        let imageview = UIImageView(image: newImage)
        //pasteboard.setData(imgData, forPasteboardType: kUTTypePNG as String)
        //pasteboard.general.image = imageview.image!
        pasteboard.image = imageview.image
        pasteboard.setData(imgData, forPasteboardType: kUTTypePNG as String)
        let proxy = self.textDocumentProxy as! UIKeyInput
    }
    
    func onSetPastBoardData(argImageName: String)
    {
        /*let pasteboard: UIPasteboard = UIPasteboard.generalPasteboard()
        let image: UIImage = currentImage!
        let newImage = scaleImage(image, toSize: CGSize(width: 40, height: 40))
        let imgData: NSData = UIImagePNGRepresentation(newImage)!
        pasteboard.setData(imgData, forPasteboardType: UIPasteboardTypeListImage[0] as! String)
        
        let proxy = UITextDocumentProxy.self as! UITextDocumentProxy
        let data = pasteboard.string!
        print(data)
        proxy.insertText(data)*/
        let pasteboard: UIPasteboard = UIPasteboard.generalPasteboard() //UIPasteboard.general
        let imageName = argImageName
        let newImage = UIImage(named: imageName)
        let imgData = UIImagePNGRepresentation(newImage)
        
        let imageview = UIImageView(image: newImage)
        //pasteboard.setData(imgData, forPasteboardType: kUTTypePNG as String)
        //pasteboard.general.image = imageview.image!
        pasteboard.image = imageview.image
        pasteboard.setData(imgData, forPasteboardType: kUTTypePNG as String)
        let proxy = self.textDocumentProxy as! UIKeyInput
        //proxy.insertText(imgData)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated
    }
    
    override func textWillChange(textInput: UITextInput) {
        // The app is about to change the document's contents. Perform any preparation here.
    }
    
    override func textDidChange(textInput: UITextInput) {
        // The app has just changed the document's contents, the document context has been updated.
        
        var textColor: UIColor
        var proxy = self.textDocumentProxy as! UITextDocumentProxy
        if proxy.keyboardAppearance == UIKeyboardAppearance.Dark {
            textColor = UIColor.whiteColor()
        } else {
            textColor = UIColor.blackColor()
        }
    }
    override func viewDidAppear(animated: Bool) {
        let expandedHeight:CGFloat = 216
        let heightConstraint = NSLayoutConstraint(item:self.view,
            attribute: .Height,
            relatedBy: .Equal,
            toItem: nil,
            attribute: .NotAnAttribute,
            multiplier: 0.0,
            constant: expandedHeight)
        self.view.addConstraint(heightConstraint)
        //self.viewMainKeyboard.addConstraint(heightConstraint)
    }
    //    @IBAction func onBtnTapChangeKeyboard(sender: UIButton) {
    //        ///println("COUNT_SUBVIEW: \(xibSmiliesLayout.subviews.count)")
    //        let proxy = self.textDocumentProxy as! UIKeyInput
    //        self.advanceToNextInputMode()
    //        //proxy.insertText("COUNT_SUBVIEW: \(xibSmiliesLayout.subviews.count) - \(totalBtn)")
    //        //self.advanceToNextInputMode()
    //    }
    //
    //    @IBAction func onBtnTapDeletLetter(sender: AnyObject) {
    //        let proxy = self.textDocumentProxy as! UIKeyInput
    //        proxy.deleteBackward()
    //    }
    @IBAction func onBtnTapKeyboardCtrl(sender: UIButton) {
        var btn = sender as UIButton
        let proxy = self.textDocumentProxy as! UIKeyInput
        if(btn.tag == 1) {
            self.advanceToNextInputMode()
        }
        else if(btn.tag == 2) {
            proxy.deleteBackward()
        }
        else if(btn.tag == 3) {
            if(onScreenDefaultKeyboard != onScreenKeyboard.SmiliesKeyboard) {
                onAttachedSmiliesKeyboard()
                onScreenDefaultKeyboard = onScreenKeyboard.SmiliesKeyboard
                onResetBottomCtrlBtnColor(btn)
            }
        }
        else if(btn.tag == 4) {
            if(onScreenDefaultKeyboard != onScreenKeyboard.FlowerKeyboard) {
                onAttachedFlowerKeyboard()
                onScreenDefaultKeyboard = onScreenKeyboard.FlowerKeyboard
                onResetBottomCtrlBtnColor(btn)
            }
        }
        else if(btn.tag == 5) {
            if(onScreenDefaultKeyboard != onScreenKeyboard.FoodKeyboard) {
                onAttachedFoodKeyboard()
                onScreenDefaultKeyboard = onScreenKeyboard.FoodKeyboard
                onResetBottomCtrlBtnColor(btn)
            }
        }
        else if(btn.tag == 6) {
            if(onScreenDefaultKeyboard != onScreenKeyboard.OthersKeyboard) {
                onAttachedOthersKeyboard()
                onScreenDefaultKeyboard = onScreenKeyboard.OthersKeyboard
                onResetBottomCtrlBtnColor(btn)
            }
        }
    }
    func onResetBottomCtrlBtnColor(pressedBtn : UIButton)
    {
        for vBtn in self.mainKeyCtrlLayoutContainer.subviews as! [UIButton] {
            vBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyCtrlBtnDefault, alpha: 1.0)
        }
        pressedBtn.backgroundColor = UIColor().HexToColor(onKeyboardColorSet.KeyCtrlBtnSelected, alpha: 1.0)
    }
}
